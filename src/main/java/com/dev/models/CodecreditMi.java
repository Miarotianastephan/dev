package com.dev.models;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;
@Entity
@Table(name="codecredit")
public class CodecreditMi{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int idcodecredit; 
    int code; 
    int etats; 
    int idvaleurcredit; 

    public CodecreditMi(){ }
    
    public int getIdcodecredit(){
        return this.idcodecredit;
    }
    public void setIdcodecredit(int idcodecredit){
        this.idcodecredit=idcodecredit;
    }
    public int getCode(){
        return this.code;
    }
    public void setCode(int code){
        this.code=code;
    }
    public int getEtats(){
        return this.etats;
    }
    public void setEtats(int etats){
        this.etats=etats;
    }
    public int getIdvaleurcredit(){
        return this.idvaleurcredit;
    }
    public void setIdvaleurcredit(int idvaleurcredit){
        this.idvaleurcredit=idvaleurcredit;
    }

}
