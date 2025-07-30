package com.kurung.diagnosis.enumeration;

import lombok.Getter;

@Getter
public enum Category {
  DIET("식습관"),
  EXERCISE("운동습관"),
  SLEEP("수면패턴"),
  STRESS("스트레스/정서"),
  CAFFEINE("카페인 섭취량"),
  ALCOHOL("음주습관"),
  SMOKING("흡연습관"),
  SMARTPHONE("스마트폰 사용습관"),
  DISEASE("질병이환");

  private final String value;

  Category(String value) {
    this.value = value;
  }

  public static Category fromValue(String value) {
    for (Category c : values()) {
      if (c.getValue().equals(value)) {
        return c;
      }
    }
    throw new IllegalArgumentException("카테고리가 없습니다 : " + value);
  }
}
