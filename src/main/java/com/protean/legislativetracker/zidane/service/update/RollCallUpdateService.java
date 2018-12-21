package com.protean.legislativetracker.zidane.service.update;

import com.protean.legislativetracker.yuna.model.RollCall;

import java.util.List;

public interface RollCallUpdateService {

    List<RollCall> saveAllRollCallChanges(List<Long> changedRollCallIds);

}
