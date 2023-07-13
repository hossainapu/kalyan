package com.codetreatise.view;

import java.util.ResourceBundle;

public enum FxmlView {

    USER {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("user.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/User.fxml";
        }
    },
    MODIFY_APPLICATION{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("application.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/ModifyApplication.fxml";
        }
    },
    APPLICATION {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("application.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/EnrollApplication.fxml";
        }
    },
    LIST_APPLICATION {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("list.application.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/ListApplication.fxml";
        }
    },
    APPLICATION_DETAILS {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("application.details");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/ApplicationDetails.fxml";
        }
    },
    APPLICATION_UPLOAD{
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("application.upload.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/UploadApplication.fxml";
        }
    },
    DASHBOARD {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("dashboard.title");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/Dashboard.fxml";
        }
    },
    LOGIN {
        @Override
		public String getTitle() {
            return getStringFromResourceBundle("login.title");
        }

        @Override
		public String getFxmlFile() {
            return "/fxml/Login.fxml";
        }
    },
    ONLINE_ENROLL_APPLICATION {
        @Override
        public String getTitle() {
            return getStringFromResourceBundle("application.enroll.online");
        }

        @Override
        public String getFxmlFile() {
            return "/fxml/EnrollOnlineApplication.fxml";
        }
    };
    
    public abstract String getTitle();
    public abstract String getFxmlFile();
    
    String getStringFromResourceBundle(String key){
        return ResourceBundle.getBundle("Bundle").getString(key);
    }

}
