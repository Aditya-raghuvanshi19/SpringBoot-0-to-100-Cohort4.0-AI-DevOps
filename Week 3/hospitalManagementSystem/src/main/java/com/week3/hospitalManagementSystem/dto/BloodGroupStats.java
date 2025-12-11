package com.week3.hospitalManagementSystem.dto;

import com.week3.hospitalManagementSystem.entities.types.BloodGroupType;
import lombok.Data;

@Data
public class BloodGroupStats {

    private final BloodGroupType bloodGroupType;
    private final Long count;
}
