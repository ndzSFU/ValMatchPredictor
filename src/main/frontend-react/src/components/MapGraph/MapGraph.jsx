import { BarChart, XAxis, YAxis, Tooltip, Bar } from 'recharts';
import { RechartsDevtools } from '@recharts/devtools';

// #region Sample data
const rangeData = [
    { day: '05-01', temperature: [0, 10] },
    { day: '05-02', temperature: [0, 15] },
    { day: '05-03', temperature: [0, 12] },
    { day: '05-04', temperature: [4, 12] },
    { day: '05-05', temperature: [12, 16] },
    { day: '05-06', temperature: [5, 16] },
    { day: '05-07', temperature: [3, 12] },
    { day: '05-08', temperature: [0, 8] },
    { day: '05-09', temperature: [0, 5] },
];

const testMapData = [
    { name: 'Bind', winRate: [0, 55]},
    { name: 'Haven', winRate: [0, 60]},
]

// #endregion
export const BarChartRangeExample = ({ isAnimationActive = true }) => (
    <BarChart
        style={{ width: '100%', maxWidth: '700px', maxHeight: '70vh', aspectRatio: 1.618 }}
        responsive
        data={testMapData}
        margin={{
            top: 20,
            right: 20,
            bottom: 20,
            left: 20,
        }}
    >
        <XAxis dataKey="name" />
        <YAxis width="auto" type="number" domain={[0, 100]} />
        <Tooltip />
        <Bar dataKey="winRate" fill="#8884d8" isAnimationActive={true} />
        <RechartsDevtools />
    </BarChart>
);

export function MapGraph({MapData, xAxisLabel, yAxisLabel, formatter}) {
    console.log("MapData in MapGraph: ", MapData);
    return(
        <BarChart
            style={{ width: '100%', maxWidth: '700px', maxHeight: '70vh', aspectRatio: 1.618 }}
            responsive
            data={MapData}
            margin={{
                top: 20,
                right: 20,
                bottom: 20,
                left: 20,
            }}
        >
            <XAxis dataKey={xAxisLabel} />
            <YAxis width="auto" type="number" domain={[0, 100]} />
            <Tooltip
                formatter={formatter}
            />
            <Bar dataKey={yAxisLabel} fill="#8884d8" isAnimationActive={true} label={{ fill: 'white', fontSize: 20 }} />
            <RechartsDevtools />
        </BarChart>
    );
}

