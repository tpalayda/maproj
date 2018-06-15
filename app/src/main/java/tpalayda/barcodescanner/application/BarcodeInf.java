package tpalayda.barcodescanner.application;

import java.util.UUID;

/**
 * Created by taras on 16/05/18.
 */

public class BarcodeInf {
    private String m_barcodeID;
    private String m_productName;
    private String m_category;
    private UUID m_id;
    private int m_price;
    private String m_date;
    private String m_other_inf;

    public BarcodeInf(String barcodeID,String productName,String category,UUID id,int price, String date, String other_inf) {
        m_barcodeID = barcodeID;
        m_productName = productName;
        m_category = category;
        m_id = id;
        m_price = price;
        m_date = date;
        m_other_inf = other_inf;
    }

    public BarcodeInf(String barcodeID,String productName,String category,UUID id, String date, String other_inf) {
        m_barcodeID = barcodeID;
        m_productName = productName;
        m_category = category;
        m_id = id;
        m_price = 0;
        m_date = date;
        m_other_inf = other_inf;
    }

    public String getBarcodeID() {
        return m_barcodeID;
    }
    public String getOtherInf()
    {
        return m_other_inf;
    }
    public void setOtherInf(String other_inf)
    {
        m_other_inf = other_inf;
    }
    public UUID getUUID(){
        return m_id;
    }
    public int getPrice(){
        return m_price;
    }
    public String getDate() { return m_date; }
    public String getProductName() {
        return m_productName;
    }
    public String getCategory() {
        return m_category;
    }

    public void setBarcodeID(String barcodeID) {
        this.m_barcodeID = barcodeID;
    }
    public void setPrice(int price) {
        this.m_price = price;
    }
    public void setProductName(String productName) {
        this.m_productName = productName;
    }
    public void setCategory(String category) {
        this.m_category = category;
    }
    public void setUUID(UUID id){ this.m_id = id; }
    public void setDate(String date) { this.m_date = date; }
}
