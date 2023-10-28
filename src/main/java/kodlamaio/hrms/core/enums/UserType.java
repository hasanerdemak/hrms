package kodlamaio.hrms.core.enums;

public enum UserType {
    SystemPersonnel {
        @Override
        public String toString() {
            return "SystemPersonnel";
        }
    },
    Employer {
        @Override
        public String toString() {
            return "Employer";
        }
    },
    JobSeeker {
        @Override
        public String toString() {
            return "JobSeeker";
        }
    }
}
