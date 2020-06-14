package xyz.seanchao.bookstore.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document(collection = "BookImage")
public class BookImage {
    @Id
    private ObjectId _id;

    @Field("id")
    private int fid;

    @Field("img")
    private String imageBase64;

    public BookImage() {
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    @Override
    public String toString() {
        return "BookImage{" +
                "_id=" + _id +
                ", fid=" + fid +
                ", imageBase64='" + imageBase64.substring(0, 100) + '\'' +
                '}';
    }
}
