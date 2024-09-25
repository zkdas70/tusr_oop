using OOP.service;

namespace OOP.model
{
    /// <summary>
    ///  класс модель дагнных Customer
    /// </summary>
    public class Customer
    {
        
        private static IdGenerator _idGenerator = new IdGenerator(); // генератор id принажаший не обьектт а классу
        private int _id;
        private LimitedSting _fulname;
        private LimitedSting _address;
        private LimitedFloat _cost;

        public Customer(string fulname, string address, float cost)
        {
            _id = _idGenerator.get_next_id();
            
            ValueValidator.AssertStringOnLength(fulname, 200, "name");
            ValueValidator.AssertStringOnLength(address, 500, "info");
            
            _fulname = new LimitedSting(200, fulname);
            _address = new LimitedSting(500, address);
        }
    
    }
}