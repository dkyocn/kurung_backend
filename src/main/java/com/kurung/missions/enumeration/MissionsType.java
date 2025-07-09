package com.kurung.missions.enumeration;

import com.kurung.common.enumeration.HealthType;

public enum MissionsType {
  DIET(HealthType.DIET),
  EXERCISE(HealthType.EXERCISE),
  STRESS(HealthType.STRESS);

  private final HealthType healthType;

  MissionsType(HealthType healthType) {
    this.healthType = healthType;
  }

  public HealthType getHealthType() {
    return healthType;
  }
}
