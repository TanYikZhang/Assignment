package com.example.assignment.Model;

public class PC {
    public static final String TABLE_NAME = "orderpc";
    public static final String COLOMN_ID = "pcid";
    public static final String COLOMN_CUSID = "cusid";
    public static final String COLOMN_DATE = "date";
    public static final String COLOMN_PROCESS = "process";
    public static final String COLOMN_CHASSIS = "chassis";
    public static final String COLOMN_MOTHERBOARD = "motherboard";
    public static final String COLOMN_CPU = "cpu";
    public static final String COLOMN_RAM = "ram";
    public static final String COLOMN_GRAPHICSCARD = "graphicscard";
    public static final String COLOMN_FIRSTSSD = "firstssd";
    public static final String COLOMN_SECONDSSD = "secondssd";
    public static final String COLOMN_HDD = "hdd";
    public static final String COLOMN_COOLINGSYSTEM = "coolingsystem";
    public static final String COLOMN_CASESLIGHTING = "caseslighting";
    public static final String COLOMN_PSU = "psu";
    public static final String COLOMN_WIRELESSLAN = "wirelesslan";
    public static final String COLOMN_OS = "OS";
    public static final String COLOMN_WARRANTY = "warranty";
    public static final String COLOMN_TOTALPRICE = "totalprice";


    private int id;
    private int cusid;
    private String DateBuild;
    private String Process;

    private String TypeChassis;
    private String TypeMotherBoard;
    private String TypeCPU;
    private String TypeRAM;
    private String TypeGraphicscard;
    private String TypeFirstSlotSSD;
    private String TypeSecondSlotSSD;
    private String TypeHardDrive;
    private String TypeCoolingSystem;
    private String TypeCasesLighting;
    private String TypePowerSupply;
    private String TypeWirelessLan;
    private String TypeOS;
    private String TypeWarrantyPackage;

    private int TotalPrice;


    public int getCusid() {
        return cusid;
    }

    public void setCusid(int cusid) {
        this.cusid = cusid;
    }


    public PC() {

    }

    public String getTypeMotherBoard() {
        return TypeMotherBoard;
    }

    public void setTypeMotherBoard(String typeMotherBoard) {
        TypeMotherBoard = typeMotherBoard;
    }

    public String getTypeChassis() {
        return TypeChassis;
    }

    public void setTypeChassis(String typeChasis) {
        TypeChassis = typeChasis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeCPU() {
        return TypeCPU;
    }

    public void setTypeCPU(String typeCPU) {
        TypeCPU = typeCPU;
    }

    public String getTypeRAM() {
        return TypeRAM;
    }

    public void setTypeRAM(String typeRAM) {
        TypeRAM = typeRAM;
    }

    public String getTypeGraphicscard() {
        return TypeGraphicscard;
    }

    public void setTypeGraphicscard(String typeGraphicscard) {
        TypeGraphicscard = typeGraphicscard;
    }

    public String getTypeFirstSlotSSD() {
        return TypeFirstSlotSSD;
    }

    public void setTypeFirstSlotSSD(String typeFirstSlotSSD) {
        TypeFirstSlotSSD = typeFirstSlotSSD;
    }

    public String getTypeSecondSlotSSD() {
        return TypeSecondSlotSSD;
    }

    public void setTypeSecondSlotSSD(String typeSecondSlotSSD) {
        TypeSecondSlotSSD = typeSecondSlotSSD;
    }

    public String getTypeHardDrive() {
        return TypeHardDrive;
    }

    public void setTypeHardDrive(String typeHardDrive) {
        TypeHardDrive = typeHardDrive;
    }

    public String getTypeCoolingSystem() {
        return TypeCoolingSystem;
    }

    public void setTypeCoolingSystem(String typeCoolingSystem) {
        TypeCoolingSystem = typeCoolingSystem;
    }


    public String getTypeCasesLighting() {
        return TypeCasesLighting;
    }

    public void setTypeCasesLighting(String typeCasesLighting) {
        TypeCasesLighting = typeCasesLighting;
    }

    public String getTypePowerSupply() {
        return TypePowerSupply;
    }

    public void setTypePowerSupply(String typePowerSupply) {
        TypePowerSupply = typePowerSupply;
    }

    public String getTypeWirelessLan() {
        return TypeWirelessLan;
    }

    public void setTypeWirelessLan(String typeWirelessLan) {
        TypeWirelessLan = typeWirelessLan;
    }

    public String getTypeOS() {
        return TypeOS;
    }

    public void setTypeOS(String typeOS) {
        TypeOS = typeOS;
    }

    public String getTypeWarrantyPackage() {
        return TypeWarrantyPackage;
    }

    public void setTypeWarrantyPackage(String typeWarrantyPackage) {
        TypeWarrantyPackage = typeWarrantyPackage;
    }

    public String getDateBuild() {
        return DateBuild;
    }

    public void setDateBuild(String dateBuild) {
        DateBuild = dateBuild;
    }

    public String getProcess() {
        return Process;
    }

    public void setProcess(String process) {
        Process = process;
    }

    public int getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        TotalPrice = totalPrice;
    }


}
