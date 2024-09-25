using System;

namespace OOP.service
{
    /// <summary>
    ///  класс лимитированой по длине строки
    /// </summary>
    public class LimitedSting
    {
        private int _length;
        private string _data;

        public LimitedSting(int length, string data)
        {
            _length = length;
            _check_length(data);
        }

        private void _check_length(string data)
        {
            if (_length < data.Length)
            {
                throw new Exception($"привышена максимальная длина поля в {_length} символов");
            }

            _data = data;
        }

        public void setData(string data)
        {
            _check_length(data);
        }

        public string get_data()
        {
            return _data;
        }
    }

    /// <summary>
    ///  класс лимитированой по длине Float числа
    /// </summary>
    public class LimitedFloat
    {
        private int _min;
        private int _max;
        private float _vale;

        public LimitedFloat(int min, int max, float vale)
        {
            _min = min;
            _max = max;
            _check_vale(vale);
        }

        private void _check_vale(float vale)
        {
            if (vale <= _min || vale >= _max)
            {
                throw new Exception($"выход за огранмченый е приделы {_min}<=x<={_max}");
            }

            _vale = vale;
        }

        public void set_value(float vale)
        {
            _check_vale(vale);
        }

        public float get_value()
        {
            return _vale;
        }
    }
}