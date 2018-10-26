package com.carfax.vin.service;

import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carfax.vin.exceptions.VinNotFoundException;
import com.carfax.vin.model.Record;
import com.carfax.vin.model.VinData;
import com.carfax.vin.util.DataReaderUtil;

@Service
public class VinService {

    @Autowired
    DataReaderUtil dataReaderUtil;

    public VinData analyzeDataPoints(String vin) {
        VinData data = dataReaderUtil.getVinData(vin);
        final ListIterator<Record> iterator = data.getRecords().listIterator();
        while (iterator.hasNext()) {
            if (iterator.hasPrevious()) {
                final Record prev = getPreviousAndResetCursor(iterator);
                final Record curr = getCurrentAndResetCursor(iterator);
                if (hasOdometerRollback(prev, curr)) {
                    curr.setIsOdometerRollback(true);
                }
            }
            iterator.next();
        }
        return data;
    }

    private boolean hasOdometerRollback(Record prev, Record curr) {
        return prev.getOdometerReading() > curr.getOdometerReading();
    }

    private Record getPreviousAndResetCursor(final ListIterator<Record> iterator) {
        final Record p = iterator.previous();
        iterator.next();
        return p;
    }

    private Record getCurrentAndResetCursor(final ListIterator<Record> iterator) {
        final Record r = iterator.next();
        iterator.previous();
        return r;
    }

}
