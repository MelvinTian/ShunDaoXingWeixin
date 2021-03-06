package com.sdx.common.mapper;

import com.sdx.common.entity.UserAddress;
import com.sdx.common.entity.UserAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserAddressMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    int countByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    int deleteByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    int deleteByPrimaryKey(Integer addrId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    int insert(UserAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    int insertSelective(UserAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    List<UserAddress> selectByExample(UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    UserAddress selectByPrimaryKey(Integer addrId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    int updateByExampleSelective(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    int updateByExample(@Param("record") UserAddress record, @Param("example") UserAddressExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    int updateByPrimaryKeySelective(UserAddress record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table user_address
     *
     * @mbggenerated Sun Jun 28 14:22:49 CST 2015
     */
    int updateByPrimaryKey(UserAddress record);
}