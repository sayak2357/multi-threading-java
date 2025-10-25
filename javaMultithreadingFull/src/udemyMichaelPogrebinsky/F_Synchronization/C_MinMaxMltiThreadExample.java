package udemyMichaelPogrebinsky.F_Synchronization;

/*
* PROBLEM
* In this exercise, we are going to implement a class called MinMaxMetrics .

A single instance of this class will be passed to multiple threads in our application.

MinMaxMetrics is an analytics class and is used to keep track of the minimum and the maximum of a particular business or performance metric in our application.
*
* */
public class C_MinMaxMltiThreadExample {


        private volatile long minValue;
        private volatile long maxValue;

        /**
         * Initializes all member variables
         */
        public C_MinMaxMltiThreadExample() {
            this.maxValue = Long.MIN_VALUE;
            this.minValue = Long.MAX_VALUE;
        }

        /**
         * Adds a new sample to our metrics.
         */
        public void addSample(long newSample) {
            synchronized (this) {
                this.minValue = Math.min(newSample, this.minValue);
                this.maxValue = Math.max(newSample, this.maxValue);
            }
        }

        /**
         * Returns the smallest sample we've seen so far.
         */
        public long getMin() {
            return this.minValue;
        }

        /**
         * Returns the biggest sample we've seen so far.
         */
        public long getMax() {
            return this.maxValue;
        }

}
