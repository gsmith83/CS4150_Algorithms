using System;
using System.Collections.Generic;
using System.Diagnostics;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Timers;

namespace TripPlanner
{
    class Program
    {
        // Returns the minimum penalty that it is possible to incur when driving from the hotel 
        // that is hotel[i] miles from New York to the final hotel, which is hotel[hotel.Length-1] 
        // miles from New York.  Assume that 0 <= i < hotel.Length and that
        // 0 = hotel[0] < hotel[1] < hotel[2] < ... < hotel[hotel.Length-1]
        public static int MinimumPenalty(int[] hotel, int i, Dictionary<int, int> cache)
        {
            
            int result;
            if(cache.TryGetValue(i, out result))
            {
                return result;
            }
            
            if (i <= 0)
                return 0;
            
            int minPenalty = Int32.MaxValue;
            int lastHotel = i;

            for(int n = i - 1; n >= 0; n--)
            {
                int thisPenalty = (int)Math.Pow((400 - (hotel[i] - hotel[n])), 2);

                if(thisPenalty < minPenalty)
                {
                    lastHotel = n;
                }

                minPenalty = Math.Min(thisPenalty, minPenalty) + MinimumPenalty(hotel, n, cache);
            }
            cache[i] = minPenalty;
            return minPenalty;
        }

        // Returns the minimum penalty that it is possible to incur when driving from the first hotel 
        // (which is in New York) to the final hotel (which is in San Francisco).  The mileage from New York
        // to each of the hotels on the route is given by the hotel array, where 
        // 0 = hotel[0] < hotel[1] < hotel[2] < ... < hotel[hotel.Length-1]
        public static int MinimumPenalty(int[] hotel)
        {
            return MinimumPenalty(hotel, hotel.Length - 1, new Dictionary<int, int>());
            //return MinimumPenalty(hotel, hotel.Length - 1);
        }

        static void Main(string[] args)
        {
            int HOTEL_COUNT = 6400;
            Random rand = new Random();

            Stopwatch timer = new Stopwatch();
            timer.Start();

            // create hotel array
            int[] hotels = new int[HOTEL_COUNT];
            int mileMarker = 0;
            for(int i = 0; i < HOTEL_COUNT; i++)
            {
                hotels[i] = mileMarker;
                mileMarker += (rand.Next(200) + 300);
            }
            
            timer.Reset();
            timer.Start();
            int minimum = MinimumPenalty(hotels);
            timer.Stop();



            Console.WriteLine(minimum + " " + timer.Elapsed);
            Console.Read();
        }
    }
}
