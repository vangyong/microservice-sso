package cn.segema.cloud.sso.client1.domain;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Member {

    private String name;

    private String code;

    private Integer gender;

    private String mobile;
}
