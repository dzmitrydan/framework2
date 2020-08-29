package model;

public class InstancesForm {

    private String numberOfInstances;
    private String whatAreTheseInstancesFor;
    private String operatingSystemSoftware;
    private String machineClass;
    private String machineType;
    private boolean checkAddGPUs;
    private String numberOfGPUs;
    private String gPUType;
    private String localSSD;
    private String datacenterLocation;
    private String commitedUsage;

    public InstancesForm() {
    }

    public InstancesForm(String numberOfInstances,
                         String whatAreTheseInstancesFor,
                         String operatingSystemSoftware,
                         String machineClass,
                         String machineType,
                         boolean checkAddGPUs,
                         String numberOfGPUs,
                         String gPUType,
                         String localSSD,
                         String datacenterLocation,
                         String commitedUsage) {
        this.numberOfInstances = numberOfInstances;
        this.whatAreTheseInstancesFor = whatAreTheseInstancesFor;
        this.operatingSystemSoftware = operatingSystemSoftware;
        this.machineClass = machineClass;
        this.machineType = machineType;
        this.checkAddGPUs = checkAddGPUs;
        this.numberOfGPUs = numberOfGPUs;
        this.gPUType = gPUType;
        this.localSSD = localSSD;
        this.datacenterLocation = datacenterLocation;
        this.commitedUsage = commitedUsage;
    }

    public InstancesForm(String machineClass,
                         String machineType,
                         String datacenterLocation,
                         String commitedUsage) {
        this.machineClass = machineClass;
        this.machineType = machineType;
        this.datacenterLocation = datacenterLocation;
        this.commitedUsage = commitedUsage;
    }

    public String getNumberOfInstances() {
        return numberOfInstances;
    }

    public void setNumberOfInstances(String numberOfInstances) {
        this.numberOfInstances = numberOfInstances;
    }

    public String getWhatAreTheseInstancesFor() {
        return whatAreTheseInstancesFor;
    }

    public void setWhatAreTheseInstancesFor(String whatAreTheseInstancesFor) {
        this.whatAreTheseInstancesFor = whatAreTheseInstancesFor;
    }

    public String getOperatingSystemSoftware() {
        return operatingSystemSoftware;
    }

    public void setOperatingSystemSoftware(String operatingSystemSoftware) {
        this.operatingSystemSoftware = operatingSystemSoftware;
    }

    public String getMachineClass() {
        return machineClass;
    }

    public void setMachineClass(String machineClass) {
        this.machineClass = machineClass;
    }

    public String getMachineType() {
        return machineType;
    }

    public void setMachineType(String machineType) {
        this.machineType = machineType;
    }

    public boolean isCheckAddGPUs() {
        return checkAddGPUs;
    }

    public void setCheckAddGPUs(boolean checkAddGPUs) {
        this.checkAddGPUs = checkAddGPUs;
    }

    public String getNumberOfGPUs() {
        return numberOfGPUs;
    }

    public void setNumberOfGPUs(String numberOfGPUs) {
        this.numberOfGPUs = numberOfGPUs;
    }

    public String getgPUType() {
        return gPUType;
    }

    public void setgPUType(String gPUType) {
        this.gPUType = gPUType;
    }

    public String getLocalSSD() {
        return localSSD;
    }

    public void setLocalSSD(String localSSD) {
        this.localSSD = localSSD;
    }

    public String getDatacenterLocation() {
        return datacenterLocation;
    }

    public void setDatacenterLocation(String datacenterLocation) {
        this.datacenterLocation = datacenterLocation;
    }

    public String getCommitedUsage() {
        return commitedUsage;
    }

    public void setCommitedUsage(String commitedUsage) {
        this.commitedUsage = commitedUsage;
    }


    @Override
    public String toString() {
        return "InstancesForm{" +
                "numberOfInstances='" + numberOfInstances + '\'' +
                ", whatAreTheseInstancesFor='" + whatAreTheseInstancesFor + '\'' +
                ", operatingSystemSoftware='" + operatingSystemSoftware + '\'' +
                ", machineClass='" + machineClass + '\'' +
                ", machineType='" + machineType + '\'' +
                ", checkAddGPUs=" + checkAddGPUs +
                ", numberOfGPUs=" + numberOfGPUs +
                ", gPUType='" + gPUType + '\'' +
                ", localSSD='" + localSSD + '\'' +
                ", datacenterLocation='" + datacenterLocation + '\'' +
                ", commitedUsage='" + commitedUsage + '\'' +
                '}';
    }

}
