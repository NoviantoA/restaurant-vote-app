package com.novianto.restaurant.app.util;

import com.novianto.restaurant.app.model.AbstractBaseEntity;
import com.novianto.restaurant.app.util.exception.IllegalRequestDataException;
import com.novianto.restaurant.app.util.exception.NotFoundException;
import org.springframework.data.domain.Persistable;

import java.util.UUID;

public class ValidationUtil {

    public static <T> T checkNotFoundWithId(T object, UUID id) {
        checkNotFoundWithId(object != null, id);
        return object;
    }

    public static void checkNotFoundWithId(Boolean found, UUID id) {
        checkNotFound(found, "id = " + id);
    }

    public static <T> T checkNotFound(T object, String message) {
        checkNotFound(object != null, message);
        return object;
    }

    public static void checkNotFound(Boolean found, String message) {
        if (!found) {
            throw new NotFoundException("Tidak ditemukan entity dengan " + message);
        }
    }

    public static void checkNew(Persistable<UUID> bean) {
        if (!bean.isNew()) {
            throw new IllegalRequestDataException(bean + " harus baru (id=null)");
        }
    }

    public static void assureIdConsistent(AbstractBaseEntity entity, UUID id) {
        if (entity.isNew()) {
            entity.setId(id);
        } else if (entity.id() != id) {
            throw new IllegalArgumentException(entity + " harus dengan id = " + id);
        }
    }
}
