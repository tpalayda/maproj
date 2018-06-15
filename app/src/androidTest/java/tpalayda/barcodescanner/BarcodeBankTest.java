package tpalayda.barcodescanner;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import tpalayda.barcodescanner.application.BarcodeBank;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by taras on 15/06/18.
 */

@RunWith(AndroidJUnit4.class)
@SmallTest
public class BarcodeBankTest {
    public static final int TEST_PRICE = 0;
    public static BarcodeBank instance;

    @Before
    public void initializeSingleton()
    {
        instance = BarcodeBank.getInstance(InstrumentationRegistry.getTargetContext());
        instance.getBarcodes().get(0).setPrice(TEST_PRICE);
    }
    @Test
    public void isInitialized()
    {
        assertThat(instance.getBarcodes().get(0).getPrice(),is(TEST_PRICE));
        assertThat(instance.getCategories().get(0),is("dasd"));
        assertThat(instance.getBarcodes().isEmpty(),is(false));
        assertThat(instance.getCategories().isEmpty(),is(false));
    }
}
