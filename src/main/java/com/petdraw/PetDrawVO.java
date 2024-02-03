package com.petdraw;

import java.util.Date;

public class PetDrawVO implements java.io.Serializable {
    private Integer petDrawId;
    private Integer	memberId;
    private Integer memberPairId;
    private Boolean isMemberLike;
    private Date memberResTime;
    private Date memberPairResTime;
    private Boolean isMemberPairLike;
    private Date petDrawTime;
    private Double petDrawLog;
    private Double petDrawLat;

    private Integer	getMemberId() {
    	return	memberId;
    }
    
    public void setMemberId(Integer memberId) {
        this.memberId = memberId;
    }
    
    public Integer getPetDrawId() {
        return petDrawId;
    }

    public void setPetDrawId(Integer petDrawId) {
        this.petDrawId = petDrawId;
    }

    public Integer getMemberPairId() {
        return memberPairId;
    }

    public void setMemberPairId(Integer memberPairId) {
        this.memberPairId = memberPairId;
    }

    public Boolean getMemberLike() {
        return isMemberLike;
    }

    public void setMemberLike(Boolean memberLike) {
        isMemberLike = memberLike;
    }

    public Date getMemberResTime() {
        return memberResTime;
    }

    public void setMemberResTime(Date memberResTime) {
        this.memberResTime = memberResTime;
    }

    public Date getMemberPairResTime() {
        return memberPairResTime;
    }

    public void setMemberPairResTime(Date memberPairResTime) {
        this.memberPairResTime = memberPairResTime;
    }

    public Boolean getMemberPairLike() {
        return isMemberPairLike;
    }

    public void setMemberPairLike(Boolean memberPairLike) {
        isMemberPairLike = memberPairLike;
    }

    public Date getPetDrawTime() {
        return petDrawTime;
    }

    public void setPetDrawTime(Date petDrawTime) {
        this.petDrawTime = petDrawTime;
    }

    public Double getPetDrawLog() {
        return petDrawLog;
    }

    public void setPetDrawLog(Double petDrawLog) {
        this.petDrawLog = petDrawLog;
    }

    public Double getPetDrawLat() {
        return petDrawLat;
    }

    public void setPetDrawLat(Double petDrawLat) {
        this.petDrawLat = petDrawLat;
    }
}
