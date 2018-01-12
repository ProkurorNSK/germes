/**
 * Domain classes used to produce the base.
 * <p>
 * These classes contain the AbstractEntity.
 * </p>
 *
 * @author ProkurorNSK
 * @version 1.0
 * @since 1.0
 */
package ru.prokurornsk.germes.app.model.entity.base;

import ru.prokurornsk.germes.app.model.entity.person.Account;

import java.time.LocalDateTime;

/**
 * Base class for all business entities.
 *
 * @author ProkurorNSK
 * @version 1.0
 */
public class AbstractEntity {
    /**
     * Unique entity identifier.
     */
    private int id;

    /**
     * Timestamp of entity creation.
     */
    private LocalDateTime createdAt;

    /**
     * Timestamp of entity last modification.
     */
    private LocalDateTime modifiedAt;

    /**
     * Person who created specific entity.
     */
    private Account createdBy;

    /**
     * Last person who modified entity.
     */
    private Account modifiedBy;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(final LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(final LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    public Account getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(final Account createdBy) {
        this.createdBy = createdBy;
    }

    public Account getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(final Account modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        AbstractEntity that = (AbstractEntity) obj;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }
}
