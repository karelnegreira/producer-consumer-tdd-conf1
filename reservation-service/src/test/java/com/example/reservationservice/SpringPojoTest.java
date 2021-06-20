package com.example.reservationservice;

import org.assertj.core.api.Assertions;
import org.junit.Assert;
import org.junit.Test;

public class SpringPojoTest {

    @Test
    public void createPojoTest() {
        Reservation res = new Reservation("1", "Karel");

        Assert.assertEquals("1", res.getId());
        Assert.assertEquals("Karel", res.getName());

        Assertions.assertThat(res)
                .as("Not a null reference")
                .isNotNull();

        Assertions.assertThat(res.getName())
                .as("Name is just Karel")
                .isNotBlank();
    }
}
