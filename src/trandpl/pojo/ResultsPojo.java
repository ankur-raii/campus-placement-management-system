/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trandpl.pojo;

/**
 *
 * @author hmayw
 */
public class ResultsPojo {
    private String pId;
    private String jId;
    private double percentage;
    private String selectedByHr;

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getjId() {
        return jId;
    }

    public void setjId(String jId) {
        this.jId = jId;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public String getSelectedByHr() {
        return selectedByHr;
    }

    public void setSelectedByHr(String selectedByHr) {
        this.selectedByHr = selectedByHr;
    }
}
