package hr.kingict.sparavec.zadatak.model;

import lombok.Data;

import java.util.Date;

@Data
public class Meta {

    private Date createdAt;
    private Date updatedAt;
    private String barcode;
    private String qrCode;
}
