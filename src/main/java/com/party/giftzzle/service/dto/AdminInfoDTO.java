package com.party.giftzzle.service.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.party.giftzzle.domain.AdminInfo;
import com.party.giftzzle.domain.enumeration.Gender;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * A DTO for the AdminInfo entity.
 */
public class AdminInfoDTO extends UserDTO implements Serializable {

  private Long id;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Gender gender;

  @NotNull
  @Temporal(TemporalType.DATE)
  private Date birthdate;

  private String postalCode;

  @NotNull
  private String city;

  @NotNull
  private String state;

  @NotNull
  private String country;

  @NotNull
  private String address;

  @JsonIgnore
  private String resetKey;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Gender getGender() {
    return gender;
  }

  public void setGender(Gender gender) {
    this.gender = gender;
  }


  public Date getBirthdate() {
    return birthdate;
  }

  public void setBirthdate(Date birthdate) {
    this.birthdate = birthdate;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public String getResetKey() {
    return resetKey;
  }

  public void setResetKey(String resetKey) {
    this.resetKey = resetKey;
  }

  public AdminInfoDTO() {
  }

  public AdminInfoDTO(AdminInfo adminInfo) {
    this.id = adminInfo.getId();
    this.gender = adminInfo.getGender();
    this.birthdate = adminInfo.getBirthdate();
    this.postalCode = adminInfo.getPostalCode();
    this.city = adminInfo.getCity();
    this.state = adminInfo.getState();
    this.country = adminInfo.getCountry();
    this.address = adminInfo.getAddress();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    AdminInfoDTO adminInfoDTO = (AdminInfoDTO) o;
    if (adminInfoDTO.getId() == null || getId() == null) {
      return false;
    }
    return Objects.equals(getId(), adminInfoDTO.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(getId());
  }

  @Override
  public String toString() {
    return "AdminInfoDTO{" +
        "id=" + getId() +
        ", gender='" + getGender() + "'" +
        ", birthdate='" + getBirthdate() + "'" +
        ", postalCode='" + getPostalCode() + "'" +
        ", city='" + getCity() + "'" +
        ", state='" + getState() + "'" +
        ", country='" + getCountry() + "'" +
        ", address='" + getAddress() + "'" +
        "}";
  }
}
