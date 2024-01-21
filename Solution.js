
/**
 * @param {Interval[][]} schedule
 * @return {Interval[]}
 */
var employeeFreeTime = function (schedule) {
    const intervalsSortedByStart = createArrayIntervalsSortedByStart(schedule);
    return findAllCommonFreeTimeIntervals(intervalsSortedByStart);
};

// function Interval is defined by leetcode.com
// do not include the function when running the code on the website
/**
 * @param {number} start
 * @param {number} end
 */
function Interval(start, end) {
    this.start = start;
    this.end = end;
}

/**
 * @param {Interval[][]} schedule
 * @return {Interval[]}
 */
function createArrayIntervalsSortedByStart(schedule) {
    return schedule.flat().sort((x, y) => x.start - y.start);
}

/**
 * @param {Interval[]} intervals
 * @return {Interval[]}
 */
function findAllCommonFreeTimeIntervals(intervals) {
    const commonFreeTimeIntervals = new Array();
    let previousEnd = intervals[0].end;

    for (let i = 1; i < intervals.length; ++i) {
        if (previousEnd < intervals[i].start) {
            commonFreeTimeIntervals.push(new Interval(previousEnd, intervals[i].start));
        }
        previousEnd = Math.max(previousEnd, intervals[i].end);
    }

    return commonFreeTimeIntervals;
}
