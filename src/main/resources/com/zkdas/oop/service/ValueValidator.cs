using System;

namespace OOP.service
{   
    /// <summary>
    ///  валидатор проверки длины поля
    /// </summary>
    public class ValueValidator
    {
        public static void AssertStringOnLength(string value, int maxLength, string propertyName)
        {
            if(value.Length > maxLength)
            {
                throw new Exception($"'{propertyName}' must be less than {maxLength} characters.");
            }
        }
    }
}