import java.util.*;

// Job class representing a job with id, deadline, and profit
class Job {
    int id, deadline, profit;

    public Job(int id, int deadline, int profit) {
        this.id = id;
        this.deadline = deadline;
        this.profit = profit;
    }
}

public class JobSequencing {
    public static void main(String[] args) {
        // Sample input: Job ID, Deadline, Profit
        Job[] jobs = {
            new Job(1, 4, 20),
            new Job(2, 1, 10),
            new Job(3, 1, 40),
            new Job(4, 1, 30)
        };

        jobScheduling(jobs, jobs.length);
    }

    // Function to find the maximum profit and sequence of jobs
    public static void jobScheduling(Job[] jobs, int n) {
        // Step 1: Sort jobs in decreasing order of profit
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Step 2: Find maximum deadline to create a time slot array
        int maxDeadline = 0;
        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // Step 3: Create an array to keep track of free time slots
        int[] slot = new int[maxDeadline + 1]; // 1-indexed
        Arrays.fill(slot, -1);

        int totalProfit = 0;
        int jobCount = 0;

        // Step 4: Assign jobs to the latest available slot before their deadline
        for (Job job : jobs) {
            for (int j = job.deadline; j > 0; j--) {
                if (slot[j] == -1) {
                    slot[j] = job.id;
                    totalProfit += job.profit;
                    jobCount++;
                    break;
                }
            }
        }

        // Step 5: Print results
        System.out.println("Total Jobs Done: " + jobCount);
        System.out.println("Total Profit: " + totalProfit);
        System.out.print("Job Sequence: ");
        for (int i = 1; i <= maxDeadline; i++) {
            if (slot[i] != -1)
                System.out.print("J" + slot[i] + " ");
        }
    }
}
