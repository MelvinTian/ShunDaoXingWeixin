package com.sdx.common.entity;

public class UserAddress {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.addr_id
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    private Integer addrId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.user_id
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    private String userId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.address
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.city
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    private String city;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.provice
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    private String provice;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column user_address.distreet
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    private String distreet;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_address.addr_id
     *
     * @return the value of user_address.addr_id
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    public Integer getAddrId() {
        return addrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_address.addr_id
     *
     * @param addrId the value for user_address.addr_id
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    public void setAddrId(Integer addrId) {
        this.addrId = addrId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_address.user_id
     *
     * @return the value of user_address.user_id
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    public String getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_address.user_id
     *
     * @param userId the value for user_address.user_id
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_address.address
     *
     * @return the value of user_address.address
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_address.address
     *
     * @param address the value for user_address.address
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_address.city
     *
     * @return the value of user_address.city
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    public String getCity() {
        return city;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_address.city
     *
     * @param city the value for user_address.city
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_address.provice
     *
     * @return the value of user_address.provice
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    public String getProvice() {
        return provice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_address.provice
     *
     * @param provice the value for user_address.provice
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    public void setProvice(String provice) {
        this.provice = provice == null ? null : provice.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column user_address.distreet
     *
     * @return the value of user_address.distreet
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    public String getDistreet() {
        return distreet;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column user_address.distreet
     *
     * @param distreet the value for user_address.distreet
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    public void setDistreet(String distreet) {
        this.distreet = distreet == null ? null : distreet.trim();
    }
}