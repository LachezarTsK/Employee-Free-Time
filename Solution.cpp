
#include <span>
#include <ranges>
#include <vector>
#include <algorithm>
using namespace std;

// class Interval is defined by leetcode.com
// do not include the class when running the code on the website
class Interval {
    
public:
    int start;
    int end;

    Interval() = default;

    Interval(int _start, int _end) {
        start = _start;
        end = _end;
    }
};

class Solution {
    
public:
    vector<Interval> employeeFreeTime(const vector<vector<Interval>>& schedule) const {
        vector<Interval> intervalsSortedByStart = createVectorIntervalsSortedByStart(schedule);
        return findAllCommonFreeTimeIntervals(intervalsSortedByStart);
    }

    vector<Interval> createVectorIntervalsSortedByStart(span<const vector<Interval>> schedule) const {
        auto viewIntervals {ranges::views::join(schedule)};
        vector<Interval> intervals(viewIntervals.begin(), viewIntervals.end());

        auto compareStart = [](const Interval& x, const Interval& y) {return x.start < y.start;};
        ranges::sort(intervals, compareStart);

        return intervals;
    }

    vector<Interval> findAllCommonFreeTimeIntervals(span<const Interval> intervals) const {
        vector<Interval> commonFreeTimeIntervals;
        int previousEnd = intervals[0].end;

        for (size_t i = 1; i < intervals.size(); ++i) {
            if (previousEnd < intervals[i].start) {
                commonFreeTimeIntervals.emplace_back(previousEnd, intervals[i].start);
            }
            previousEnd = max(previousEnd, intervals[i].end);
        }

        return commonFreeTimeIntervals;
    }
};
