namespace OOP.service
{
    /// <summary>
    ///  класс для генерации id
    /// </summary>
    public class IdGenerator
    {
        private int _lastId;

        public IdGenerator()
        {
            _lastId = 0;
        }

        public int get_next_id()
        {
            _lastId++;
            return _lastId;
        }
    }
}