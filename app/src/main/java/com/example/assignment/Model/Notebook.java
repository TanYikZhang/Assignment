package com.example.assignment.Model;

public class Notebook {

    private String  TypeCPU,TypeGraphicscard,TypeDisplay,
                    TypeRAM,TypeKeyboard,TypeFirstM2SSDBaY,TypeSecondM2SSDBay,
                    TypeHardDrive,TypeOS,TypeWarrantyPackage;
    private int TotalPrice;

    public Notebook(){

    }

    public void setTypeCPU(String typeCPU) {
        TypeCPU = typeCPU;
    }

    public String getTypeCPU() {
        return TypeCPU;
    }

    public void setTypeGraphicscard(String typeGraphicscard) {
        TypeGraphicscard = typeGraphicscard;
    }

    public String getTypeGraphicscard() {
        return TypeGraphicscard;
    }

    public void setTypeDisplay(String typeDisplay) {
        TypeDisplay = typeDisplay;
    }

    public String getTypeDisplay() {
        return TypeDisplay;
    }

    public void setTypeRAM(String typeRAM) {
        TypeRAM = typeRAM;
    }

    public String getTypeRAM() {
        return TypeRAM;
    }

    public void setTypeKeyboard(String typeKeyboard) {
        TypeKeyboard = typeKeyboard;
    }

    public String getTypeKeyboard() {
        return TypeKeyboard;
    }

    public void setTypeFirstM2SSDBaY(String typeFirstM2SSDBaY) {
        TypeFirstM2SSDBaY = typeFirstM2SSDBaY;
    }

    public String getTypeFirstM2SSDBaY() {
        return TypeFirstM2SSDBaY;
    }

    public void setTypeSecondM2SSDBay(String typeSecondM2SSDBay) {
        TypeSecondM2SSDBay = typeSecondM2SSDBay;
    }

    public String getTypeSecondM2SSDBay() {
        return TypeSecondM2SSDBay;
    }

    public void setTypeHardDrive(String typeHardDrive) {
        TypeHardDrive = typeHardDrive;
    }

    public String getTypeHardDrive() {
        return TypeHardDrive;
    }

    public void setTypeOS(String typeOS) {
        TypeOS = typeOS;
    }

    public String getTypeOS() {
        return TypeOS;
    }

    public void setTypeWarrantyPackage(String typeWarrantyPackage) {
        TypeWarrantyPackage = typeWarrantyPackage;
    }

    public String getTypeWarrantyPackage() {
        return TypeWarrantyPackage;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }
}
