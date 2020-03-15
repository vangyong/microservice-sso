package cn.segema.cloud.sso.server.repository;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import cn.segema.cloud.sso.server.domain.MobileCode;


@Repository
public interface MobileCodeRepository extends JpaRepository<MobileCode, BigInteger> {
	
	 @Query("SELECT v from MobileCode v  where v.mobileNumber = ?1 ") 
	 public MobileCode findByMobileNumber(String mobileNumber); 
	 
	 
	 @Query("SELECT v from MobileCode v  where v.mobileNumber = ?1 and  v.code = ?2") 
     public MobileCode findByMobileNumberCode(String mobileNumber,String code); 
	
}
