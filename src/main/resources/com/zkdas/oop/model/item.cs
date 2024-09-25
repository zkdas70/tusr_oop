using System;
using OOP.service;

namespace OOP.model
{
    public class Item
    {
        /// <summary>
        ///  класс модель дагнных Item
        /// </summary>
        private static IdGenerator _idGenerator = new IdGenerator();
        private int _id;
        private LimitedSting _name;
        private LimitedSting _info;
        private LimitedFloat _cost;

        public Item(string name, string info, float cost)
        {
            _id = _idGenerator.get_next_id();
            
            ValueValidator.AssertStringOnLength(name, 200, "name");
            ValueValidator.AssertStringOnLength(info, 1000, "info");
            
            _name = new LimitedSting(200, name);
            _info = new LimitedSting(1000, info);
            _cost = new LimitedFloat(0, 100_000, cost);
        }
    }
}