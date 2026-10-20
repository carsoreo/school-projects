package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Classes with this interface can have time conflicts with an {@link Activity}.
 * @author Zane Taylor
 */
public interface Conflict {

    /**
     * Checks if this object has a time conflict with another activity, even if only one minute overlaps.
     * This function is commutative, {@code a.checkConflict(b)} should give the same result as
     * {@code b.checkConflict(a)}.
     * @param possibleConflictingActivity another activity
     * @throws ConflictException if the two activities conflict
     */
    void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
