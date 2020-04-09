package cn.gyt.bs.entity;

import lombok.Data;

@Data
public class Area {

    /**
     * 地区id
     */
    private int id;

    /**
     * 地区名称
     */
    private String name;

    /**
     * 父Id
     */
    private int pid;
}
