package com.slibs.slibs.repositories.support;

import com.slibs.slibs.entities.License;

import lombok.Data;

/**
 * Сущность фильтра для логики.
 * <br/>
 * Позволяет разделить абстракцию и реализацию, уменьшая связность.
 * <br/>
 * В слое, где непосредственно применяются фильтры, должно быть наследование от этого класса.
 */
public abstract class SearchFilter {
}
