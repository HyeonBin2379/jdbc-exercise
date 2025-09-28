package exercise_v1.constant.user.validation;

import exercise_v1.constant.user.InputMessage;
import exercise_v1.constant.user.ManagerPage;
import exercise_v1.constant.user.UserPage;
import exercise_v1.domain.user.User;
import exercise_v1.exception.user.UnableToReadUserException;
import exercise_v1.exception.user.UserNotApprovedException;
import exercise_v1.exception.user.UserNotDeletedException;
import exercise_v1.exception.user.UserNotHavePermissionException;
import exercise_v1.exception.user.UserNotRestoredException;
import exercise_v1.exception.user.UserNotUpdatedException;
import exercise_v1.exception.user.UserRoleNotDeletedException;
import exercise_v1.exception.user.UserRoleNotUpdatedException;
import java.util.List;

public class UserManagementValidCheck {

    public void checkMenuNum(String regex, String menuNum) {
        if (!menuNum.matches(regex)) {
            throw new IllegalArgumentException(InputMessage.INVALID_MENU_NUMBER.toString());
        }
    }

    public void checkUserType(String userType) {
        if (userType == null) {
            throw new UnableToReadUserException(UserPage.CANNOT_SEARCH_USER.toString());
        }
    }

    public void checkUserFound(User foundUser) {
        if (foundUser == null) {
            throw new UnableToReadUserException(UserPage.CANNOT_SEARCH_USER.toString());
        }
    }
    public void checkUserFound(List<User> users) {
        if (users.isEmpty()) {
            throw new UnableToReadUserException(UserPage.CANNOT_SEARCH_USER.toString());
        }
    }

    public void checkPermission(boolean notHavePermission) {
        if (notHavePermission) {
            throw new UserNotHavePermissionException(ManagerPage.NOT_HAVE_PERMISSION.toString());
        }
    }

    public void checkPermission(String position, String allowedPosition, boolean deleteOption) {
        if (!position.equals(allowedPosition)) {
            switch (allowedPosition) {
                case "총관리자":
                    throw new UserNotHavePermissionException(ManagerPage.NOT_HAVE_PERMISSION.toString());
                case "창고관리자":
                    if (deleteOption) {
                        throw new UserNotHavePermissionException(ManagerPage.CHIEF_MANAGER_CANNOT_DELETE.toString());
                    }
            }
        }
    }

    public void checkUserUpdated(boolean isUserUpdated) {
        if (!isUserUpdated) {
            throw new UserNotUpdatedException(UserPage.USER_UPDATE_FAILED.toString());
        }
    }

    public void checkUserApproved(boolean isUserApproved, boolean restoreOption) {
        if (!isUserApproved) {
            if (!restoreOption) {
                throw new UserNotApprovedException(ManagerPage.APPROVE_FAILED.toString());
            } else {
                throw new UserNotRestoredException(ManagerPage.RESTORE_FAILED.toString());
            }
        }
    }

    public void checkUserDeleted(boolean isUserDeleted) {
        if (!isUserDeleted) {
            throw new UserNotDeletedException(UserPage.USER_DELETE_FAILED.toString());
        }
    }

    public void checkRoleExist(String userRole) {
        if (userRole == null) {
            throw new UserRoleNotUpdatedException(ManagerPage.ALREADY_DELETED_OR_NOT_EXIST.toString());
        }
    }

    public void checkRoleUpdated(boolean isRoleUpdated) {
        if (!isRoleUpdated) {
            throw new UserRoleNotUpdatedException(ManagerPage.ROLE_UPDATE_FAILED.toString());
        }
    }

    public void checkRoleDeleted(boolean isRoleDeleted) {
        if (!isRoleDeleted) {
            throw new UserRoleNotDeletedException(ManagerPage.ROLE_DELETE_FAILED.toString());
        }
    }

    public void checkRoleDeleted(String userRole) {
        if (userRole != null) {
            throw new UserRoleNotDeletedException(ManagerPage.ALREADY_HAVE_ROLE.toString());
        }
    }

    public void checkDeleteRoleAvailable(String userRole) {
        if (userRole == null) {
            throw new UserRoleNotDeletedException(ManagerPage.ALREADY_DELETED_OR_NOT_EXIST.toString());
        }
        if (userRole.equals("총관리자")) {
            throw new UserNotHavePermissionException(ManagerPage.CHIEF_MANAGER_CANNOT_DELETE.toString());
        }
    }
}
