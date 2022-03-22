    package KruskalOnGraph.utils;

    public class DataRecord {
        private String cityFrom;
        private String cityTo;
        private float distance;

        /**
         * Creates a new record
         */
        public DataRecord(String cityFrom, String cityTo, float distance) {
            this.cityFrom = cityFrom;
            this.cityTo = cityTo;
            this.distance = distance;
        }

        /**
         * Returns this record's starting city
         */
        public String getCityFrom() {
            return this.cityFrom;
        }

        /**
         * Returns this record's arrival city
         */
        public String getCityTo() {
            return this.cityTo;
        }

        /**
         * Returns this record's distance
         */
        public float getDistance() {
            return this.distance;
        }

    }
