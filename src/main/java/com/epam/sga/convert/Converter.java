package com.epam.sga.convert;

public interface Converter<I, O> {
	O convert(I i);
}
