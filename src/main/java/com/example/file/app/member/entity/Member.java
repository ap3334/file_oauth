package com.example.file.app.member.entity;

import com.example.file.app.base.AppConfig;
import com.example.file.app.base.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.io.File;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString(callSuper = true)
public class Member extends BaseEntity {

    @Column(unique = true)
    private String username;

    @JsonIgnore
    private String password;

    private String email;

    private String profileImg;

    public void removeProfileImgOnStorage() {
        if (profileImg == null || profileImg.trim().length() == 0) return;

        String profileImgPath = getProfileImgPath();

        new File(profileImgPath).delete();
    }

    private String getProfileImgPath() {
        return AppConfig.GET_FILE_DIR_PATH + "/" + profileImg;
    }

    public String getProfileImgUrl() {
        if ( profileImg == null ) return null;

        return "/gen/" + profileImg;
    }

    public Member(long id) {
        super(id);
    }

}
