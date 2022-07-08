package com.cigrastudio.booklibrary;

public class ModelClass {
        String name,age,hobby,userid;

        public ModelClass() {
        }

        public ModelClass(String name, String age, String hobby, String userid) {
                this.name = name;
                this.age = age;
                this.hobby = hobby;
                this.userid = userid;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getAge() {
                return age;
        }

        public void setAge(String age) {
                this.age = age;
        }

        public String getHobby() {
                return hobby;
        }

        public void setHobby(String hobby) {
                this.hobby = hobby;
        }

        public String getUserid() {
                return userid;
        }

        public void setUserid(String userid) {
                this.userid = userid;
        }
}
