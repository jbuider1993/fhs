// 快速时间选择选项
let datePickerOptions = {
    shortcuts: [{
        text: '今天',
        onClick(picker) {
            const end = new Date();
            end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);
            const start = new Date();
            start.setHours(0);
            start.setMinutes(0);
            start.setSeconds(0);
            picker.$emit('pick', [start, end]);
        }
    }, {
        text: '昨天',
        onClick(picker) {
            const oneDayTime = 24 * 60 * 60 * 1000;
            const end = new Date();
            end.setTime(end.getTime() - oneDayTime)
            end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);
            const start = new Date();
            start.setTime(start.getTime() - oneDayTime)
            start.setHours(0);
            start.setMinutes(0);
            start.setSeconds(0);
            picker.$emit('pick', [start, end]);
        }
    }, {
        text: '最近两天',
        onClick(picker) {
            const oneDayTime = 24 * 60 * 60 * 1000;
            const end = new Date();
            end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);
            const start = new Date();
            start.setTime(start.getTime() - oneDayTime)
            start.setHours(0);
            start.setMinutes(0);
            start.setSeconds(0);
            picker.$emit('pick', [start, end]);
        }
    }, {
        text: '最近七天',
        onClick(picker) {
            const oneDayTime = 24 * 60 * 60 * 1000;
            const end = new Date();
            end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);
            const start = new Date();
            start.setTime(start.getTime() - oneDayTime * 6)
            start.setHours(0);
            start.setMinutes(0);
            start.setSeconds(0);
            picker.$emit('pick', [start, end]);
        }
    }, {
        text: '最近十天',
        onClick(picker) {
            const oneDayTime = 24 * 60 * 60 * 1000;
            const end = new Date();
            end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);
            const start = new Date();
            start.setTime(start.getTime() - oneDayTime * 9)
            start.setHours(0);
            start.setMinutes(0);
            start.setSeconds(0);
            picker.$emit('pick', [start, end]);
        }
    }]
};

let datePickerOptionsNotContainToday = {
    shortcuts: [{
        text: '昨天',
        onClick(picker) {
            const oneDayTime = 24 * 60 * 60 * 1000;
            const end = new Date();
            end.setTime(end.getTime() - oneDayTime)
            end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);
            const start = new Date();
            start.setTime(start.getTime() - oneDayTime)
            start.setHours(0);
            start.setMinutes(0);
            start.setSeconds(0);
            picker.$emit('pick', [start, end]);
        }
    }, {
        text: '过去两天',
        onClick(picker) {
            const oneDayTime = 24 * 60 * 60 * 1000;
            const end = new Date();
            end.setTime(end.getTime() - oneDayTime)
            end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);
            const start = new Date();
            start.setTime(start.getTime() - oneDayTime*2)
            start.setHours(0);
            start.setMinutes(0);
            start.setSeconds(0);
            picker.$emit('pick', [start, end]);
        }
    }, {
        text: '过去七天',
        onClick(picker) {
            const oneDayTime = 24 * 60 * 60 * 1000;
            const end = new Date();
            end.setTime(end.getTime() - oneDayTime)
            end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);
            const start = new Date();
            start.setTime(start.getTime() - oneDayTime * 7)
            start.setHours(0);
            start.setMinutes(0);
            start.setSeconds(0);
            picker.$emit('pick', [start, end]);
        }
    }, {
        text: '过去十天',
        onClick(picker) {
            const oneDayTime = 24 * 60 * 60 * 1000;
            const end = new Date();
            end.setTime(end.getTime() - oneDayTime)
            end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);
            const start = new Date();
            start.setTime(start.getTime() - oneDayTime * 10)
            start.setHours(0);
            start.setMinutes(0);
            start.setSeconds(0);
            picker.$emit('pick', [start, end]);
        }
    }, {
        text: '过去三十天',
        onClick(picker) {
            const oneDayTime = 24 * 60 * 60 * 1000;
            const end = new Date();
            end.setTime(end.getTime() - oneDayTime)
            end.setHours(23);
            end.setMinutes(59);
            end.setSeconds(59);
            const start = new Date();
            start.setTime(start.getTime() - oneDayTime * 30)
            start.setHours(0);
            start.setMinutes(0);
            start.setSeconds(0);
            picker.$emit('pick', [start, end]);
        }
    }]
};

export {datePickerOptions, datePickerOptionsNotContainToday};