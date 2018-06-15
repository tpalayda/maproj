package tpalayda.barcodescanner;

import android.support.test.filters.SmallTest;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

import tpalayda.barcodescanner.application.BarcodeInf;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by taras on 15/06/18.
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class BarcodeInfTest {
    public static final String TEST_STRING = "This is a string";
    public static final long TEST_LONG = 12345678L;
    public static final int TEST_INT = 32000;
    public static final UUID TEST_UUID = UUID.randomUUID();
    private BarcodeInf m_barcodeInf;

    @Before
    public void createBarcode()
    {
        m_barcodeInf = new BarcodeInf("","","", UUID.randomUUID(),"","");
    }
    @Test
    public void barcode_WriteRead()
    {
        m_barcodeInf.setCategory(TEST_STRING);
        m_barcodeInf.setProductName(TEST_STRING);
        m_barcodeInf.setBarcodeID(TEST_STRING);
        m_barcodeInf.setDate(TEST_STRING);
        m_barcodeInf.setUUID(TEST_UUID);
        m_barcodeInf.setOtherInf(TEST_STRING);
        m_barcodeInf.setPrice(TEST_INT);

        assertThat(m_barcodeInf.getBarcodeID(),is(TEST_STRING));
        assertThat(m_barcodeInf.getCategory(),is(TEST_STRING));
        assertThat(m_barcodeInf.getDate(),is(TEST_STRING));
        assertThat(m_barcodeInf.getProductName(),is(TEST_STRING));
        assertThat(m_barcodeInf.getOtherInf(),is(TEST_STRING));
        assertThat(m_barcodeInf.getPrice(),is(TEST_INT));
        assertThat(m_barcodeInf.getUUID(),is(TEST_UUID));
    }

}
