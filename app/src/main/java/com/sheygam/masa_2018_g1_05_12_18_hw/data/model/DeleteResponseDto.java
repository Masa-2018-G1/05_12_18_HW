package com.sheygam.masa_2018_g1_05_12_18_hw.data.model;

public class DeleteResponseDto {
    private String status;

    public DeleteResponseDto() {
    }

    public DeleteResponseDto(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "DeleteResponseDto{" +
                "status='" + status + '\'' +
                '}';
    }
}
