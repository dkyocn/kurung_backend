package com.kurung.favorites.enumeration;

import com.kurung.common.enumeration.HealthType;

public enum FavoritesType {
  DIET(HealthType.DIET),
  EXERCISE(HealthType.EXERCISE),
  STRESS(HealthType.STRESS);

  private final HealthType healthType;

  FavoritesType(HealthType healthType) {
    this.healthType = healthType;
  }

  public HealthType getHealthType() {
    return healthType;
  }
}
