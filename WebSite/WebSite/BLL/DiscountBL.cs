﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using NFCShoppingWebSite.DAL;
using NFCShoppingWebSite.Access_Data;

namespace NFCShoppingWebSite.BLL
{
    public class DiscountBL:IDisposable
    {
        IDiscountRepository mRepository = new DiscountRepository();

        /*查询某一期所有的优惠信息*/
        public Discount GetDiscountByID(Int32 discountID)
        {
            try
            {
                return mRepository.GetDiscounts().Single(s => s.discountID == discountID);
            }
            catch (Exception ex)
            {
                // TODO: Add handling code here.

                return null;
            }
        }

        /*查询所有的优惠信息*/
        public IEnumerable<Discount> GetDiscounts()
        {
            return mRepository.GetDiscounts();
        }

      
        public void InsertDiscount(Discount discount)
        {
            try
            {
                mRepository.InsertDiscount(discount, true);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public void DeleteDiscount(Discount discount)
        {
            try
            {
                mRepository.DeleteDiscount(discount, true);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        public void UpdateDiscount(Discount discount, Discount origDiscount)
        {
            try
            {
                mRepository.UpdateDiscount(discount, origDiscount, true);
            }
            catch (Exception ex)
            {
                throw ex;
            }
        }

        private bool mIsDisposed = false;

        protected virtual void Dispose(bool disposing)
        {
            if (!this.mIsDisposed)
            {
                if (disposing)
                {
                    mRepository.Dispose();
                }
            }
            this.mIsDisposed = true;
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }

    }
}