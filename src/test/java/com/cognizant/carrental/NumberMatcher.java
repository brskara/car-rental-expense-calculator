package com.cognizant.carrental;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Hamcrest's Matcher.is method has problems with comparing doubles,
 * NumberMatcher class should be used for this purpose.
 *
 */
class NumberMatcher extends TypeSafeMatcher<Number> {

    private double value;

    public NumberMatcher(double value) {
        this.value = value;
    }

    @Override
    public void describeTo(Description description) {
        // some description
    }

    @Override
    protected boolean matchesSafely(Number item) {
        return item.doubleValue() == value;
    }

}
