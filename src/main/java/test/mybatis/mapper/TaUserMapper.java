package test.mybatis.mapper;

import java.util.List;
import test.mybatis.pojo.TaUser;

public interface TaUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_user
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_user
     *
     * @mbggenerated
     */
    int insert(TaUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_user
     *
     * @mbggenerated
     */
    TaUser selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_user
     *
     * @mbggenerated
     */
    List<TaUser> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ta_user
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(TaUser record);
}