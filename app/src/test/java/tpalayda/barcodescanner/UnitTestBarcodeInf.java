package tpalayda.barcodescanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import tpalayda.barcodescanner.application.BarcodeBank;
import tpalayda.barcodescanner.application.BarcodeInf;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by taras on 15/06/18.
 */
@RunWith(MockitoJUnitRunner.class)
public class UnitTestBarcodeInf {
    public static final UUID TEST_UUID = UUID.randomUUID();
    BarcodeInf barcodeInf = new BarcodeInf("","","", TEST_UUID,"","");
    @Test
    public void barcode_validator()
    {
        assertThat(barcodeInf.getPrice(),is(0));
        assertThat(barcodeInf.getProductName(),is(""));
        assertThat(barcodeInf.getDate(),is(""));
        assertThat(barcodeInf.getCategory(),is(""));
        assertThat(barcodeInf.getOtherInf(),is(""));
        assertThat(barcodeInf.getUUID(),is(TEST_UUID));
    }
}
