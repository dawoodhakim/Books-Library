package com.cigrastudio.booklibrary;

public class ModelClass {
        String name,age,hobby,userid,book_name,category,description,bookimg,imageurl,uploadedby;

        public ModelClass() {
        }

        public ModelClass(String name, String age, String hobby, String userid,String book_name,String category,String description,String bookimg,String imageurl,String uploadedby) {
                this.name = name;
                this.uploadedby=uploadedby;
                this.imageurl=imageurl;
                this.age = age;
                this.hobby = hobby;
                this.userid = userid;
                this.book_name=book_name;
                this.category=category;
                this.description=description;
                this.bookimg=bookimg;

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

        public String getBook_name() {
                return book_name;
        }

        public void setBook_name(String book_name) {
                this.book_name = book_name;
        }

        public String getCategory() {
                return category;
        }

        public void setCategory(String category) {
                this.category = category;
        }

        public String getDescription() {
                return description;
        }

        public void setDescription(String description) {
                this.description = description;
        }

        public String getBookimg() {
                return bookimg;
        }

        public void setBookimg(String bookimg) {
                this.bookimg = bookimg;
        }

        public String getImageurl() {
                return imageurl;
        }

        public void setImageurl(String imageurl) {
                this.imageurl = imageurl;
        }

        public String getUploadedby() {
                return uploadedby;
        }

        public void setUploadedby(String uploadedby) {
                this.uploadedby = uploadedby;
        }
}
