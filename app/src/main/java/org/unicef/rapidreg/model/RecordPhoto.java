package org.unicef.rapidreg.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.data.Blob;
import com.raizlabs.android.dbflow.structure.BaseModel;

public class RecordPhoto extends BaseModel {
    @Column
    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    Blob photo;

    @Column
    int order;

    @Column
    String key;

    @Column
    boolean isSynced;

    public RecordPhoto() {
    }

    public RecordPhoto(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(Blob photo) {
        this.photo = photo;
    }

    public boolean isSynced() {
        return isSynced;
    }

    public void setSynced(boolean synced) {
        isSynced = synced;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String toString() {
        return "RecordPhoto{" +
                "id=" + id +
                ", photo=" + photo +
                ", order=" + order +
                ", key='" + key + '\'' +
                ", isSynced=" + isSynced +
                '}';
    }
}

